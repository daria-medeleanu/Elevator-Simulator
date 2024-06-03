import {useState, useEffect} from 'react';
import { MdDelete } from "react-icons/md";
import axios from 'axios';
import './PersonMenu.css';

const PersonMenu = () => {
    const [persons, setPersons] = useState([]);
    const [newPersonName, setNewPersonName] = useState("");
    const [newPersonFloor, setNewPersonFloor] = useState("");

    const fetchPersons = async () => {
        try{
            const response = await axios.get('http://localhost:8082/persons');
            setPersons(response.data);
        }catch(error){
            console.error('Error fetching persons:', error);
        }
    };
    useEffect(()=>{
        fetchPersons();
    },[]);
    const handleAddPerson = async (e) => {
        e.preventDefault();
        try{
            const response = await axios.post('http://localhost:8082/persons', {
                name: newPersonName,
                currentFloor : parseInt(newPersonFloor)
            });
            setNewPersonName(''); 
            setNewPersonFloor('');
            fetchPersons();
        }catch(error){
            console.log("Error adding person: ", error);
        }
    };
    const handleDeletePerson = async (id) => {
        try{
            await axios.delete(`http://localhost:8082/persons/${id}`);
            fetchPersons();
        }catch(error){
            console.error('Error deleting a person:', error);
        }
    };

    return (
        <div>
            <h1>Persons</h1>
            <ul>
                {persons.map((person) => {
                    return (
                        <div key={person.id} className="list-persons">
                            <li>{person.name} is at floor {person.currentFloor}</li>
                            <div className="remove-name" onClick={() => handleDeletePerson(person.id)}>  < MdDelete /> </div>
                        </div>
                    )
                })}
            </ul>
            <form onSubmit={handleAddPerson}>
                <label>
                    Name:
                    <input 
                        type="text"
                        value={newPersonName}
                        onChange={(e)=>setNewPersonName(e.target.value)}
                    />
                </label>
                <label>
                    Floor:
                    <input 
                        type="number"
                        value={newPersonFloor}
                        onChange={(e)=>setNewPersonFloor(e.target.value)}
                    />
                </label>
                <button type="submit" >Add person</button>
            </form>
        </div>
    )
}

export default PersonMenu
