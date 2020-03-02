package hu.elte.anyaelmentem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {

		System.out.println( "Hello Dóra!" );
        SpringApplication.run(App.class, args);
 
    }
}
