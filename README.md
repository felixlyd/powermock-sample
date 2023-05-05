# 前言
本文代码于[GitHub - felixlyd/powermock-sample: 基于powermock进行单元测试demo](https://github.com/felixlyd/powermock-sample)

[powermock.pdf](https://www.yuque.com/attachments/yuque/0/2022/pdf/22002231/1672111195851-43b09804-535b-4973-b9ee-603b52f969ce.pdf)
## 一、为什么使用PowerMock？
### 1. Mock的理念及使用场景
#### 概念及使用场景
Mock在单元测试过程中一般指模拟对象的行为，一般用于以下场景：
1）依赖的对象无法获得，例如相关代码未实现或未交付。
2）依赖的对象构造用例时极为复杂，例如目的是测试A，A依赖于B，构造B的输入十分麻烦。
3）依赖的对象为第三方接口，可能遇到网络通信异常、逻辑分支处理过多、协调配合测试麻烦等问题。
4）依赖的对象为数据库，需要数据隔离（也可以使用H2内存数据库测试，或者springbootTest的rollback功能）
#### mock的方法

- 手写mock：编写mockDao继承原有的Dao类，并覆写方法代替实际的数据库操作（见代码`demo-01`）。手写mock的前提是Dao类通过Service类的构造函数传参加载的类属性，而非利用注解@Autowired注入，也不能是Service类中某个方法实例化的局部变量，因此**手写mock的代码侵入性高、局限性大**
- mock框架使用：jmock/easymock/mockito，而这些框架不能mock带有static、final、private关键字的方法。（注：mocktio于3.4.0版本后支持静态方法的mock）
### 2. PowerMock的强大之处
能在编译时修改字节码（.class文件）来完成测试。

1. 能mock局部变量的类和方法
2. 能mock类中的静态方法
3. 能mock带有final关键字的类和方法

**注：以下是发挥powermock强大之处的两个关键注解**
```java
@RunWith(PowerMockRunner.class)
@PrepareForTest( { YourClassWithTestMethod.class })
```
## 二、如何使用PowerMock？
### 1. PowerMock简介
**前置知识**

1. mock理念
2. 单元测试理念及junit框架

PowerMock并非从头编写的mock测试框架，而是基于其他测试框架API编写的。目前兼容

-  mock框架：`powermock-easymock-api`/`powermock-mockito-api` 
-  测试框架：``powermock-junit-module`/`powermock-testng-module` 

**本文着重于**`**powermock-mockito-api**`**和**`**powermock-junit-module**`
### 2. 快速开始
（见代码`demo-02`）
```java
public class UserServiceImplTest {

    /**
     * demo-02：基于powermock的单元测试
     */
    @Test
    public void testQueryUserCount() {
        // mock类
        UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
        // 模拟方法
        PowerMockito.doReturn(10).when(userDao).getUserCount();
        UserService userService = new UserServiceImpl();
        // 注入到UserService
        Whitebox.setInternalState(userService, "userDao", userDao);
        // 调用模拟方法
        int result = userService.queryUserCount();
        // 验证
        Assert.assertEquals(10, result);
    }
}
```
### 3. 对类进行Mock的语法
#### 1）对类或接口进行Mock
##### **通过构造函数传参加载**
（见代码`demo-03`）
```java
// mock类
UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
// 通过构造函数传参
UserService userService = new UserServiceWithConstructorImpl(userDao);
```
##### 注入
（见代码`demo-02`）
```java
// mock类
UserDao userDao = PowerMockito.mock(UserDaoImpl.class);
UserService userService = new UserServiceImpl();
// 注入到UserService
Whitebox.setInternalState(userService, "userDao", userDao);
```
补充`Mockito`提供的注解（见代码`demo-19`）：
```java
// 需被mock的类
@Mock
private  UserDaoImpl userDaoImpl;

// 需要注入mock类的类
@InjectMocks
private UserService userService = new UserServiceImpl();

// 将@Mock注解的类注入到@InjectMocks注解的类
MockitoAnnotations.initMocks(this);
```
相比于`Powermock`，`Mockito`的注解写出的代码更好看
> Q：既想使用`Mockito`的注解，又想使用`Powermock`的mock静态、final，怎么办？
>  
> A：`Powermock`本身兼容`Mockito`的API，可以使用其语法。为了能mock静态、final，一定要用到`@RunWith(PowerMockRunner)`和`@PrepareForTest`

##### **局部变量new关键字**
（见代码`demo-03`）
```java
// mock类
UserDaoImpl userDaoImpl = PowerMockito.mock(UserDaoImpl.class);
// 当new一个类时，引入该类
PowerMockito.whenNew(UserDaoImpl.class).withNoArguments().thenReturn(userDaoImpl);
```
需引入
```java
// **必须**
@RunWith(PowerMockRunner.class)
// 准备需修改字节码的class：引入局部变量类的类
@PrepareForTest(UserServiceWithLocalVarImpl.class)
```
#### 2）对final关键字的类进行Mock
（见代码`demo-05`）
语法同[1）对类或接口进行Mock](#anchor1)，并且需要引入
```java
// **必须**
@RunWith(PowerMockRunner.class)
// 准备需修改字节码的class：引入final类的类和final类
@PrepareForTest({UserServiceWithFinalImpl.class, UserDaoWithFinalImpl.class})
```
#### 3）对"静态类"进行Mock
（见代码`demo-06`）
```java
PowerMockito.mockStatic(CommonService.class);
// 该类中调用了CommonService提供的静态方法
UserService userService = new UserServiceImpl();
```
并且需要引入
```java
// **必须**
@RunWith(PowerMockRunner.class)
// 准备需修改字节码的class：静态类本身
@PrepareForTest(CommonService.class)
```
> Q：一个类中既有其他类提供的静态方法又有该类提供的普通方法时该如何Mock？
>  
> A：既需要mock普通方法又需要mock静态方法（见代码`demo-07`）

> Q：如果“静态类”中有需要初始化的私有属性怎么办？
>  
> A：使用`@SuppressStaticInitializationFor(静态类类名位置)`的注解（见代码`demo-20`）

#### 4）通过spy来“模拟”真实类
**mock是什么？spy是什么？**
首先，这里的mock指具体的`PowerMockito.mock()`,spy是指具体的`PowerMockito.spy()`。前者会生成mock类，其中的方法都不会是真实的方法，返回值都是null或空collection；后者是监视一个真实对象，如果没有模拟方法，则会调用真实的方法。
**spy类的语法**
（见代码`demo-08`、`demo-09`）
```java
// spy一个类
UserDao userDao = PowerMockito.spy(new UserDaoImpl());
UserService userService = new UserServiceImpl();
// 注入（也可以是局部变量whenNew和构造函数传参）
Whitebox.setInternalState(userService, "userDao", userDao);
```
> Q：对一个接口进行spy会怎么样？
> A：从spy类的语法可以看出，spy是模拟一个真实的对象。因此对一个接口进行spy必须先实现该接口中的所有方法。

### 4. 模拟方法操作的语法
#### 1）do...when...风格(推荐)
##### 有返回值
```java
PowerMockito.doReturn(returnObject).when(mockedClass).toMockMethod(argsIfExists);
```
##### 无返回值
```java
PowerMockito.doNothing().when(mockedClass).toMockMethod(argsIfExists);
```
##### static、final、private等关键字下语法
1 static（见代码`demo-06`）、private（见代码`demo-17`）
```java
// 注意区别
PowerMockito.doReturn(returnObject).when(MockedStaticClass.class,"toMockMethodName","argsIfExists");
```
2 final（见代码`demo-05`）
与[普通风格](#anchor2)一致
##### 其他
1 抛出异常：（见代码`demo-10`）
`PowerMockito.doThrow()`
2 执行特定应答：
`PowerMockito.doAnswer()`
3 执行真实方法：（不推荐，请使用spy来做）
`PowerMockito.doCallRealMethod()`
#### 2）when...then...风格
##### 有返回值
```java
PowerMockito.when(mockedClass.toMockMethod(argsIfExists)).thenReturn(returnObject);
```
##### 无返回值
无，不支持
##### static、final、private等关键字下语法
1 static、private
```java
PowerMockito.when(MockedStaticClass.class,"toMockMethodName","argsIfExists").thenReturn(returnObject);
```
2 final
与普通风格一致
##### 其他
1 抛出异常：（见代码`demo-10`）
`PowerMockito.when().thenThrow()`
2 执行特定应答：
`PowerMockito.when().thenAnswer()`
3 执行真实方法：（不推荐，请使用spy来做）
`PowerMockito.when().thenCallRealMethod()`
> Q：这两种语法风格有什么区别吗？
> A：一般而言，除了`when...then...`缺少`doNothing()`，没有其他区别。但是，使用spy时，`when...then...`会调用具体的逻辑。（见代码`demo-11`）

### 5. 对结果进行验证的语法
#### 1）有返回值
直接用`org.junit.Assert`进行断言，例如：
```java
Assert.assertEquals(excepted, actual);
Assert.assertNotEquals(unexcepted, actual);
Assert.assertNull(object);
Assert.assertNotNull(object);
Assert.assertTrue(condition);
Assert.assertFalse(condition);
Assert.fail(msg);
```
#### 2）无返回值
##### 普通
（见代码`demo-12`）
```java
// Mockito.times(1)代表调用1次，不传此参数默认为1次
// Mockito.never()可以表示从未调用
Mockito.verify(mockedClass, Mockito.times(1)).mockedMehthod(Mockito.any(Arg.class));
```
##### 静态
（见代码`demo-13`）
```java
// 调用模拟方法
userService.saveUserWithStaticMethod(userDO);
// 告诉PowerMockito.verifyStatic需要验证几次
PowerMockito.verifyStatic(CommonService.class, Mockito.times(1));
// 告诉PowerMockito.verifyStatic需要验证的调用逻辑
userService.saveUserWithStaticMethod(Mockito.any(UserDO.class));
```
##### 私有
（见代码`demo-17`）
```java
// 验证私有方法
PowerMockito.verifyPrivate(userService,Mockito.times(1)).invoke("isOk");
```
#### 3）调用顺序验证
（见代码`demo-18`）
```java
InOrder inOrder = Mockito.inOrder(userDao);
inOrder.verify(userDao).getUserCount();
inOrder.verify(userDao).insertUser(userDO);
inOrder.verify(userDao).queryUserPhoneNumber(userDO);
```
#### 4）异常验证
在进行单元测试时，如果预期结果是抛出异常，则可以使用 JUnit 的 @Test 注解的 expected 属性来声明期望的异常类型。
下面是一个使用 @Test 注解的 expected 属性进行单元测试的简单例子：
```java
import org.junit.Test;

public class MyTest {
    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        int a = 1 / 0;
    }
}

```
在这个例子中，我们使用 @Test 注解的 expected 属性声明了期望的异常类型为 ArithmeticException。如果在运行 testDivisionByZero() 方法时，抛出了 ArithmeticException 异常，则该测试用例就会通过；如果没有抛出 ArithmeticException 异常，则该测试用例就会失败。
另外，还可以使用 try-catch 语句来测试是否抛出了期望的异常。下面是一个使用 try-catch 语句进行单元测试的简单例子：
```java
import org.junit.Test;

public class MyTest {
    @Test
    public void testDivisionByZero() {
        try {
            int a = 1 / 0;
            fail("Expected ArithmeticException");
        } catch (ArithmeticException e) {
            // 异常处理
        }
    }
}

```
在这个例子中，我们使用 try-catch 语句来测试是否抛出了 ArithmeticException 异常。如果没有抛出异常，则调用 fail() 方法，使测试用例失败。如果抛出了 ArithmeticException 异常，则该测试用例就会通过。
## 三、 使用PowerMockRunner进行单元测试
回顾[PowerMock的强大之处](#anchor3)一节，经过[第二大节](#anchor4)的内容，想必对`@RunWith(PowerMockRunner.class)`和`@PrepareForTest`两个注解有了更深的认识。
> Q：没有这两个注解会怎么样？写错了会怎么样？
> A：将无法使用PowerMock的强大能力。并且经过笔者测试，仅引入这两个注解，完全使用`Mockito`的语法，也能够引入对static、final、private的mock，但这里不做过多延伸，感兴趣的人可以自行测试。

## 四、其他API介绍
#### 1）PowerMockito.WhenNew()：参数构造器
##### 无参构造
`PowerMockito.whenNew(ToMockClass.class).withNoArguments()`
##### 有参构造
`PowerMockito.whenNew(ToMockClass.class).withArguments()`
#### 2）参数匹配
##### 精确匹配
`Mockito.eq()`
##### 模糊匹配（不推荐）
`Mockito.startsWith()`
`Mockito.endsWith()`
##### 任意匹配
`Mockito.any(Class.class)`
`Mockito.anyInt()`
`......`
##### 自定义匹配
`Mockito.argThat(i->i.equals(1))`
（见代码`demo-14`）
#### 3）自定义应答：实现Mocktio.Answer接口
##### 根据参数自定义应答（见代码`demo-15`）
```java
PowerMockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                UserDO arg = invocationOnMock.getArgument(0);
                if(arg.getName().equals(userNameA)){
                    return phoneNumberA;
                } else if (arg.getName().equals(userNameB)) {
                    return phoneNumberB;
                }else {
                    throw new RuntimeException("未知参数");
                }
            }
        })
```
##### 根据参数获取返回值
```java
PowerMockito.doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
            int arg = invocationOnMock.getArgument(0);
           	return arg+1;
        }
    })
```
##### 保存中间变量并取出（见代码`demo-16`）
```java
public class MyAnswer implements Answer<String> {
    private final UserDO userDO = new UserDO();

    @Override
    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
        UserDO arg = invocationOnMock.getArgument(0);
        userDO.setName(arg.getName());
        if(arg.getName().equals("zhangSan")){
            return "123456";
        } else {
            throw new RuntimeException("未知参数");
        }
    }

    public UserDO getUserDO() {
        return userDO;
    }
}
```
#### 4）SpringRunner冲突：引入PowerMockRule（不推荐）
避免同时使用两者，提高了单元测试编写的复杂性和难度
## 五、附录
### 1.pom.xml引入依赖
```xml
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit4</artifactId>
    <version>2.0.9</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito2</artifactId>
    <version>2.0.9</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13</version>
    <scope>test</scope>
</dependency>
```
# Mock单元测试
[[Solved] IllegalStateException: Could not initialize plugin MockMaker](https://howtodoinjava.com/mockito/plugin-mockmaker-error/)

[SpringRunner vs SpringBootTest](https://stackoverflow.com/questions/58901288/springrunner-vs-springboottest)

[Mockito asks to add @PrepareForTest for the class even after adding @PrepareForTest](https://stackoverflow.com/questions/37925034/mockito-asks-to-add-preparefortest-for-the-class-even-after-adding-preparefort)

[Powermockito一些遇到的坑 - iWuYc - 博客园](https://www.cnblogs.com/iwuyc/p/PowermockitoNotes.html)

[Mockito 3.6.0 中文文档](http://blog.fcj.one/mockito-zh-doc.html)

[Mockito - mockito-core 4.9.0 javadoc](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
