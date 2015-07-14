CREATE TABLE catalogo_productos.empresas (
       id_empresa INTEGER NOT NULL AUTO_INCREMENT
     , nombre VARCHAR(100) NOT NULL
     , descripcion TEXT
     , oculto BOOLEAN NOT NULL
     , id_archivo_logo INTEGER NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_empresa)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.monedas (
       cod_moneda CHAR(3) NOT NULL
     , nombre VARCHAR(100) NOT NULL
     , descripcion TEXT
     , valor FLOAT NOT NULL
     , oculto BOOLEAN NOT NULL
     , obsoleta BOOLEAN
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (cod_moneda)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.caracteristicas (
       id_caracteristica CHAR(32) NOT NULL
     , nombre VARCHAR(100) NOT NULL
     , descripcion VARCHAR(100)
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_bd VARCHAR(10)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_caracteristica)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.usuarios (
       usuario VARCHAR(100) NOT NULL AUTO_INCREMENT
     , nombre VARCHAR(100) NOT NULL
     , apellido VARCHAR(100)
     , clave VARCHAR(100) NOT NULL
     , email VARCHAR(100) NOT NULL
     , borrado BOOLEAN NOT NULL
     , bloqueado BOOLEAN NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (usuario)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.archivos (
       md5_archivos CHAR(32) NOT NULL
     , ruta VARCHAR(100) NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_bd VARCHAR(10)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (md5_archivos)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.archivos_subidos (
       id_archivo_subido INTEGER NOT NULL AUTO_INCREMENT
     , nombre VARCHAR(100) NOT NULL
     , md5_archivos CHAR(32) NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , agregado_por VARCHAR(100)
     , agregado_por_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_archivo_subido)
     , INDEX (md5_archivos)
     , CONSTRAINT FK_archivos_subidos_1 FOREIGN KEY (md5_archivos)
                  REFERENCES catalogo_productos.archivos (md5_archivos)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.productos (
       id_producto INTEGER NOT NULL AUTO_INCREMENT
     , nombre VARCHAR(100) NOT NULL
     , descripcion TEXT
     , oculto BOOLEAN NOT NULL
     , id_archivo_subido INTEGER NOT NULL
     , id_empresa INTEGER NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_producto)
     , INDEX (id_archivo_subido)
     , CONSTRAINT FK_productos_1 FOREIGN KEY (id_archivo_subido)
                  REFERENCES catalogo_productos.archivos_subidos (id_archivo_subido)
     , INDEX (id_empresa)
     , CONSTRAINT FK_productos_2 FOREIGN KEY (id_empresa)
                  REFERENCES catalogo_productos.empresas (id_empresa)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.precios (
       id_precio INTEGER NOT NULL AUTO_INCREMENT
     , nombre VARCHAR(100) NOT NULL
     , descripcion TEXT
     , valor FLOAT NOT NULL
     , cod_moneda CHAR(3) NOT NULL
     , oculto BOOLEAN NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_precio)
     , INDEX (cod_moneda)
     , CONSTRAINT FK_precios_1 FOREIGN KEY (cod_moneda)
                  REFERENCES catalogo_productos.monedas (cod_moneda)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.caracteristicas_x_productos (
       id_caracteristica_x_producto CHAR(3) NOT NULL
     , id_caracteristica CHAR(32) NOT NULL
     , id_producto INTEGER NOT NULL
     , valor TEXT NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_caracteristica_x_producto)
     , INDEX (id_caracteristica)
     , CONSTRAINT FK_caracteristicas_x_productos_1 FOREIGN KEY (id_caracteristica)
                  REFERENCES catalogo_productos.caracteristicas (id_caracteristica)
     , INDEX (id_producto)
     , CONSTRAINT FK_caracteristicas_x_productos_2 FOREIGN KEY (id_producto)
                  REFERENCES catalogo_productos.productos (id_producto)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.documentos_x_productos (
       id_documento_x_producto CHAR(3) NOT NULL
     , id_archivo_subido INTEGER NOT NULL
     , id_producto INTEGER NOT NULL
     , valor TEXT NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_documento_x_producto)
     , INDEX (id_archivo_subido)
     , CONSTRAINT FK_documentos_x_productos_1 FOREIGN KEY (id_archivo_subido)
                  REFERENCES catalogo_productos.archivos_subidos (id_archivo_subido)
) ENGINE InnoDB;

CREATE TABLE catalogo_productos.precios_x_productos (
       id_precio_x_producto CHAR(3) NOT NULL
     , id_precio INTEGER NOT NULL
     , id_producto INTEGER NOT NULL
     , fecha_agregado DATETIME NOT NULL
     , creado_por VARCHAR(100)
     , creado_por_usuario_bd VARCHAR(100)
     , fecha_actualizado DATETIME
     , actualizado_por VARCHAR(100)
     , actualizado_por_bd VARCHAR(100)
     , PRIMARY KEY (id_precio_x_producto)
     , INDEX (id_precio)
     , CONSTRAINT FK_precios_x_productos_1 FOREIGN KEY (id_precio)
                  REFERENCES catalogo_productos.precios (id_precio)
     , INDEX (id_producto)
     , CONSTRAINT FK_precios_x_productos_2 FOREIGN KEY (id_producto)
                  REFERENCES catalogo_productos.productos (id_producto)
) ENGINE InnoDB;

