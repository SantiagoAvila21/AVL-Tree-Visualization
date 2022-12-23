import './form.css'
import { useState } from 'react';


const Form = ({text, placeholder, handleClick, refInput}) => {
    const [node, setNode] = useState(0);
    
    const handleChange = event => {
        setNode(event.target.value);
    };

    return (
        <div className='form-container'>
            <h3>{text}</h3>
            <input ref = {refInput} className='input-cont' placeholder={placeholder} onChange={handleChange} ></input>
            <button className='formBtn' onClick={() => handleClick(node)}>Send</button>
        </div>
    );
}

export default Form;