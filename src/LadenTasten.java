import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LadenTasten {
 
    // Мой Super-Клас
    static Tasten meineTasten;
 
  public LadenTasten() { }
 
 
  public static void main(String[] args) {
 
	  try {
		    Class<?> clazz = Class.forName(Tasten.class.getName());
		    Constructor<?> ctor = clazz.getConstructor();
		    Tasten app = (Tasten)ctor.newInstance();

		    System.out.println(app.toString());
		}
		catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		    e.printStackTrace();
		}
	  System.out.println("ok!");
  }
}