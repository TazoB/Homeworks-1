CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,

    warehouse_id BIGINT,
    shop_id BIGINT,

    CONSTRAINT fk_product_warehouse
        FOREIGN KEY (warehouse_id)
        REFERENCES warehouse(id),

    CONSTRAINT fk_product_shop
        FOREIGN KEY (shop_id)
        REFERENCES shop(id)
);