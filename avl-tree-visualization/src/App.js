import './App.css'
import './styles.css'
import { Tree } from 'react-tree-graph';
import Title from './components/Title';
import Form from './components/Form';
import { useState, useRef, useEffect } from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const baseURL = "http://localhost:8080/api/avl/";
let nodes = [];


function App() {
    const [data, setData] = useState({});
    const inputRefDel = useRef(null);
    const inputRefAdd = useRef(null);

    const handleInsertClick = node => {
        inputRefAdd.current.value = null;
        if(nodes.includes(node)){
            toast.error('Node already exists in the Tree!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                progress: undefined,
                theme: "light",
            });
            return;
        }
        nodes.push(node);
        axios.post(baseURL + "update/" + node).then(res => {
            setData(res.data);
            toast.success(node + ' Has been inserted', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                progress: undefined,
                theme: "light",
            });
        });
    }

    const handleDeleteClick = node => {
        inputRefDel.current.value = null;
        const index = nodes.indexOf(node);
        if (index > -1) nodes.splice(index, 1);
        axios.delete(baseURL + "delete/" + node).then(res => {
            setData(res.data);
            toast.success(node + ' Has been deleted!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                progress: undefined,
                theme: "light",
            });
        });
    }

    const handleClearClick = () => {
        nodes = [];
        axios.get(baseURL + "clear").then(res => {
            setData(res.data);
            toast.error('Tree cleared Succesfully!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                progress: undefined,
                theme: "light",
            });
        })
    }
    
    useEffect(() => {
        axios.get(baseURL + "clear").then(res => {
            setData(res.data);
        })
    }, []);

    return (
        <div className="App">
            <ToastContainer />
            <Title title = "AVL Tree Visualization" />
            <Tree
                data={data}
                height={490}
                width={490}
                svgProps={{
                    transform: 'rotate(90)',
                    className: 'custom'
                }}
                textProps={{
                    transform: 'rotate(-90)',
                }}
            />
            <div className='formsContainer'>
                <Form text = "AddNode" placeholder= 'Type node to insert' handleClick={(node) => handleInsertClick(node)} refInput = {inputRefAdd} />
                <Form text = "DeleteNode" placeholder= 'Type node to delete' handleClick={(node) => handleDeleteClick(node)} refInput = {inputRefDel} />
                <button className='clearBtn' onClick={handleClearClick}> Clear Tree </button>
            </div>
        </div>
    );
}

export default App;
