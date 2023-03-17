import React, { useState } from "react";

const Todo = (props) => {
    // useState는 리액트의 훅중 하나이며 함수형 컴포넌트에서 상태 변수를 사용할 수 있도록 해줌
    // 훅을 이용하면 리액트가 제공하는 기능과 상태변수를 사용할 수 있다.
    // 더 구체적으로 말하면 리액트가 제공하는 useState와 같은 일련의 함수들을 훅이라고 한다.
    /*
        상태변수 :
            시간이 지남에 따라 또 컴포넌트의 사용자가 컴포넌트와 상호작용하는 동안 변경되는 변수
    */
    const [item, setItem] = useState(props.item);

    return (
        <div className="Todo">
            <input
                type="checkbox"
                id={item.id}
                name={item.id}
                value={item.done}
            />
            <label for={item.id}>{item.title}</label>
        </div>
    );
};
export default Todo;
