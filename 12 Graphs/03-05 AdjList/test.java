public class test
{
   public static void main(String[] args)
   {
      One var1 = new Two();
      One var2 = new Three();
      One var3 = new Four();
      Three var4 = new Four();
      Object var5 = new Three();
      Object var6 = new One();

      var5.method1();
   }
}

class One
{
   public void method1()
   {
      System.out.println("One1");
   }
   public void method2()
   {
      System.out.println("One2");
   }
}

class Two extends One
{
   public void method2()
   {
      System.out.println("Two2");
   }
   public void method3()
   {
      System.out.println("Two3");
   }
}

class Three extends One
{
   public void method2()
   {
      System.out.println("Three2");
      method1();
   }
}

class Four extends Three
{
   public void method1()
   {
      System.out.println("Four1");
      super.method1();
   }
   public void method3()
   {
      System.out.println("Four3");
   }
}
