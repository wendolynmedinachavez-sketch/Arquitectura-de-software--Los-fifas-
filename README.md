# Sistema Intermediario de Pólizas (`Polizas-Arq`)

Sistema de escritorio desarrollado en **Java Swing + Spring Boot** para la materia de **Arquitectura de Software** (UACM – Plantel San Lorenzo Tezonco). Actúa como intermediario entre el **Sistema Dueño** (`http://nachintoch.mx:8080`) y una base de datos local en **PostgreSQL**, sincronizando operaciones CRUD de **Clientes**, **Pólizas** y **Beneficiarios**.

## Equipo: Los Fifas
* Eduardo
* Wendolyn
* Dana
* Ángel
* Dany
* Christian Jahir

## Repositorio
https://github.com/wendolynmedinachavez-sketch/Arquitectura-de-software--Los-fifas-

---

## Requisitos previos
Antes de instalar y ejecutar el proyecto, asegúrate de contar con:
1. **Java JDK 17 o superior** instalado y configurado en la variable `JAVA_HOME`.
2. **Apache Maven 3.8+** (o un IDE compatible como Apache NetBeans / IntelliJ IDEA).
3. **PostgreSQL 14 o superior** en ejecución local.
4. **Conexión a internet** para comunicarse con la API externa (`http://nachintoch.mx:8080`).

---

## Instalación y configuración

### 1. Clonar el repositorio
```bash
git clone [https://github.com/wendolynmedinachavez-sketch/Arquitectura-de-software--Los-fifas-.git](https://github.com/wendolynmedinachavez-sketch/Arquitectura-de-software--Los-fifas-.git)
cd Arquitectura-de-software--Los-fifas-
```

### 2. Configurar la base de datos PostgreSQL
1. Crea una base de datos vacía en PostgreSQL.
2. Abre el archivo `src/main/resources/application.properties` y coloca los datos de tu PostgreSQL local:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/TU_BASE_DE_DATOS
spring.datasource.username=TU_USUARIO_POSTGRES
spring.datasource.password=TU_CONTRASEÑA_POSTGRES
spring.jpa.hibernate.ddl-auto=update
```

*(Con `ddl-auto=update`, Hibernate crea y actualiza automáticamente las tablas `cliente`, `poliza` y `beneficiario_poliza` al iniciar).*

### 3. Descargar dependencias y compilar
```bash
mvn clean install -DskipTests
```

---

## Ejecución del sistema

### Opción A: Desde NetBeans (recomendado)
1. Abre el proyecto Maven en **Apache NetBeans**.
2. Ubica la clase principal `com.mycompany.polizas.arq.PolizasArq`.
3. Ejecuta **`PolizasArq.java`** (`Shift + F6`).
   * **Importante:** No ejecutes `MenuPrincipal.java` directamente, porque `PolizasArq.java` es la clase que inicia Spring Boot (`headless(false)`), aplica el tema `FlatDarkLaf` e inyecta los controladores y vistas con `@Autowired`.

### Opción B: Desde consola con Maven
```bash
mvn spring-boot:run
```

### Opción C: Generar y ejecutar archivo `.jar`
```bash
mvn clean package -DskipTests
java -jar target/*.jar
```

---

## Módulos implementados (MVP)
* **Clientes:** Alta (`AltaCliente`), Consulta (`consultaCliente`) y Modificación (`modificarCliente`).
* **Pólizas:** Alta con generación automática de UUID (`altaPoliza`), Consulta multicriterio (`consultaPoliza`), Modificación (`modificarPoliza`) y Eliminación sincronizada (`eliminacionPoliza`).
* **Beneficiarios:** Alta (`altaBeneficiario`) y Consulta (`consultaBeneficiario`).