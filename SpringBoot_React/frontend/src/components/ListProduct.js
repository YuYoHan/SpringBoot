import React, { useRef, useEffect, useState } from "react";
import "./main.css";
import ProductItem from "./ProductItem";

function ListProduct() {
    const [items, setProductList] = useState([]);

    function getList(url) {
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                setProductList(data);
            });
    }
    useEffect(() => {
        getList("/list");
    }, []);
    return (
        <>
            <h2>상품 목록</h2>
            등록된 상품 수 : {items.length}
            <br /> <br />
            <div
                style={{
                    display: "grid",
                    gridAutoRows: "1fr",
                    gridAutoColumns: "1fr 1fr 1fr 1fr 1fr",
                }}
            >
                {items.map(
                    ({ product_code, product_name, price, filename }) => (
                        <ProductItem
                            product_code={product_code}
                            product_name={product_name}
                            price={price}
                            fileName={filename}
                            key={product_code}
                        />
                    )
                )}
            </div>
        </>
    );
}
export default ListProduct;
