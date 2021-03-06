package codetoanalyze.java.eradicate;

import javax.annotation.Nullable;

class SubclassExample {

  class T {
    public void f() {
    }
  }

  class A {

    public T foo() {
      return new T();
    }

    public
    @Nullable
    T bar() {
      return null;
    }

    public void deref(@Nullable T t) {
      if (t != null) {
        t.f();
      }
    }

    public void noDeref(T t) {
    }

  }

  class B extends A {

    public
    @Nullable
    T foo() {
      return null;
    }

    public T bar() {
      return new T();
    }

  }

  interface I {
    public T baz();
  }

  class C implements I {

    public
    @Nullable
    T baz() {
      return null;
    }
  }

  class D extends A {

    public void deref(T t) {
      t.f();
    }

    public void noDeref(@Nullable T t) {
      if (t != null) {
        t.f();
      }
    }

  }
}

public class InconsistentSubclassAnnotation {

  public static void callFromSuperclass(SubclassExample.A a) {
    SubclassExample.T t = a.foo();
    t.f();
  }

  public static void callWithNullableParam(SubclassExample.A a, @Nullable SubclassExample.T t) {
    a.deref(t);
  }

}
