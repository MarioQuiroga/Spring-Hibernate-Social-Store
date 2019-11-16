# Curso de Spring y Hibernate - Laboratorio 4
## Spring Data + anotaciones + transacciones

### Objetivos
* Comprender el funcionamiento de DI basado en anotaciones.
* Crear una capa de acceso a datos basada en Spring Data.
* Configurar una aplicación desde cero.

### Duración estimada
2 horas

### Enunciado del problema
Partiendo de una aplicación vacía (lo único provisto es el archivo `pom.xml` de configuración 
de las dependencias), se pide crear todos los archivos necesarios para una aplicación, incluyendo:
* Capa de acceso a datos:
  * Entidades anotadas JPA.
  * Repositorios de Spring Data para accederlas.
  * Clase de configuración (Java Config) para configurar `dataSource`, `entityManagerFactory` y `transactionManager`.
  * JUnits para testear los métodos de query que se definen en el repositorio.  
* Una capa de servicio:
  * Las clases de servicio reciben los repositorios que necesitan via DI en constructores.
  * Pueden realizar validaciones y delegan según corresponda a la capa de acceso a datos.
  * Los métodos del servicio que hagan escrituras a la base deben estar anotados con `@Transactional`.
  * JUnits para testear que los métodos de servicio delegan correctamente a la capa de acceso a datos.  
* Clase para iniciar la aplicación que tenga un método `main()` que inicialice el `ApplicationContext` a partir de las clases anotadas con `@Configuration`.

Recomendaciones varias:
* No usar archivo xml para configurar Spring, hacer todo con anotaciones.
* Elegir un modelo de datos de entre 3 y 5 entidades. Los laboratorios que siguen van a 
continuar el desarrollo de esta aplicación, por lo tanto tratar de trabajar en un dominio
que les sea de interés.

Algunas opciones a modo de sugerencia:
* Sistema para reserva de turnos.
* Sistema de venta o reserva de tickets para espectáculos.
* Carga de datos para un juego de Prode (pronósticos deportivos).
* Etc. (usar la imaginación).

### Preparación del entorno
Importar en Eclipse el archivo `springhibernate-lab04.zip`.

### Desarrollo
Manos a la obra.
