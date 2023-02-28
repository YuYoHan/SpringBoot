import "./App.css";
import { Route, Routes } from "react-router";
import { BrowserRouter } from "react-router-dom";
import ListProduct from "./components/ListProduct";

function App() {
    console.warn = function no_console() {};
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<ListProduct />} />
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;
