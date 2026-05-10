CREATE TABLE shop_warehouse (
    shop_id INTEGER NOT NULL,
    warehouse_id INTEGER NOT NULL,

    PRIMARY KEY (shop_id, warehouse_id),

    CONSTRAINT fk_sw_shop
        FOREIGN KEY (shop_id)
        REFERENCES shop(id),

    CONSTRAINT fk_sw_warehouse
        FOREIGN KEY (warehouse_id)
        REFERENCES warehouse(id)
);