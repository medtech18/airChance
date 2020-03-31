package controller.common;

public class ComboxItem
{
   private int key;
   private String value;
   
   public ComboxItem( int key, String value)
   {
       this.key = key;
       this.value = value;
   }

   @Override
   public String toString()
   {
       return value;
   }

   public int getKey()
   {
       return key;
   }

   public String getValue()
   {
       return value;
   }
}