import "./App.css";
import { useState } from "react";

function App() {
    const [names, setName] = useState(["김철수", "마이클", "박상수"]);
    return (
        <div className="App">
            {names[0]}
            <br />
            {names[1]}
            <br />
            {names[2]}
            <br />
        </div>
    );
}

export default App;
