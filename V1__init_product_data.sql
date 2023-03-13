CREATE TABLE IF NOT EXISTS product_groups
(
    name         VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITHOUT TIME ZONE,
    date_updated TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT product_groups_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS products
(
    name               VARCHAR(255) NOT NULL,
    amount             INTEGER      NOT NULL,
    article            VARCHAR(255) NOT NULL,
    date_created       TIMESTAMP WITHOUT TIME ZONE,
    purchase_price     DECIMAL      NOT NULL,
    sale_price         DECIMAL      NOT NULL,
    date_updated       TIMESTAMP WITHOUT TIME ZONE,
    product_group_name VARCHAR(255) NOT NULL,
    warehouse_name     VARCHAR(255) NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS warehouses
(
    name         VARCHAR(255) NOT NULL,
    date_created TIMESTAMP WITHOUT TIME ZONE,
    date_updated TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT warehouses_pkey PRIMARY KEY (name)
);

INSERT INTO warehouses(name, date_created, date_updated)
VALUES ('Hookah-cat', '2023-03-12 20:26:24.904000', '2023-03-12 20:26:24.904000');

INSERT INTO product_groups(name, date_created, date_updated)
VALUES ('Tobacco', '2023-03-12 20:26:24.904000', '2023-03-12 20:26:24.904000');

INSERT INTO products(name, amount, article, date_created, purchase_price, sale_price, date_updated, product_group_name,
                     warehouse_name)
VALUES ('420', '100', 'FF4341F53NH', '2023-03-12 20:26:24.904000', '150.50', '200.75', '2023-03-12 20:26:24.904000', 'Tobacco',
        'Hookah-cat');
