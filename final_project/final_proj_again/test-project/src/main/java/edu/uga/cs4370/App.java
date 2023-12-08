package edu.uga.cs4370;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan()
@EnableJpaRepositories()
public class App 
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
}
