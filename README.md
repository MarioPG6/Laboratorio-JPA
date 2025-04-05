# JAX-RS Template Application

This is a template for a lightweight RESTful API using JAX-RS. The sample code is a call for getting the current time.
    
## Running the application locally

First build with:

    $mvn clean install

Then run it with:

    $ java -cp target/classes:target/dependency/* com.example.Main

Al agregar la anotación @OneToMany(cascade=ALL, mappedBy="competitor") en la entidad Competitor, se establece una relación bidireccional entre Competitor y Producto, en la que un competidor puede estar asociado a múltiples productos. Esta configuración indica que la entidad Competitor es la parte inversa de la relación, mientras que la entidad Producto es la parte propietaria (debido a que posee la anotación @ManyToOne). El atributo mappedBy="competitor" le dice a JPA que no cree una columna adicional para gestionar la relación, ya que esta ya está representada en la tabla Producto a través del campo competitor. Como resultado, al revisar la base de datos, la tabla Producto contiene una columna que referencia al id del Competitor, representando la relación de muchos productos con un único competidor. Esta estructura permite, además, que al eliminar un competidor, se eliminen automáticamente todos los productos relacionados si se mantiene el cascade tipo ALL.


![image](https://github.com/user-attachments/assets/4fa5b19e-2a42-4157-b3f7-50e198355b2e)

![image](https://github.com/user-attachments/assets/fa1c2e01-2082-4b69-ac85-00562df102e9)

