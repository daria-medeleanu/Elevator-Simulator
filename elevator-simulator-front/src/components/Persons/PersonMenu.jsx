import {useState, useEffect} from 'react';
import { MdDelete } from "react-icons/md";
import axios from 'axios';
import './PersonMenu.css';

const PersonMenu = () => {
    const [persons, setPersons] = useState([]);
    const [newPersonName, setNewPersonName] = useState("");
    const [newPersonFloor, setNewPersonFloor] = useState("");
    const [selectedPerson, setSelectedPerson] = useState(null);
    const [destinationFloor, setDestinationFloor] = useState("");
    const [direction, setDirection] = useState('up');


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
                currentFloor : parseInt(newPersonFloor),
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
    const handleRequestElevator = async (person) => {
        try{
            const response = await axios.post('http://localhost:8082/persons/request-elevator',{
                person, 
                direction
            });
            setSelectedPerson(person);
            console.log("Elevator assigned: ", response.data);
        }catch(error){
            console.error("Error requesting elevator:", error);
        }
    };

    const handleSetDestination = async (e) => {
        e.preventDefault();
        const body = {
            personId: selectedPerson.id,
            destinationFloor: parseInt(destinationFloor)
        }
        try{
            console.log(body);
            await axios.post('http://localhost:8082/persons/set-destination', body);
            setDestinationFloor('');
            setSelectedPerson(null);
            fetchPersons();
        }catch(error){
            console.log("Error setting destination: ", error);
        }
    };

    return (
        <div className="person">
            <h1>Persons</h1>
            <ul>
                {persons.map((person) => (
                    <div key={person.id} className="list-persons">
                        <div className="person-list">
                            <div className="person-list-content">
                                {person.name} is at floor {person.currentFloor}
                            </div>
                            <div className="remove-person-button" onClick={()=>handleDeletePerson(person.id)}>
                                <MdDelete />
                            </div>
                        </div>
                        <div className="make-request-button" onClick={()=>handleRequestElevator(person)} >
                            Make request
                        </div>
                    </div>
                ))}
            </ul>
            <form onSubmit={handleAddPerson} className="person">
                <label>
                    Name:
                    <input 
                        type="text"
                        value={newPersonName}
                        onChange={(e)=>setNewPersonName(e.target.value)}
                    />
                </label>
                <label>
                    Current floor:
                    <input 
                        type="number"
                        value={newPersonFloor}
                        onChange={(e)=>setNewPersonFloor(e.target.value)}
                    />
                </label>
                <button type="submit" >Add person</button>
            </form>
            {selectedPerson && (
                <form onSubmit={handleSetDestination} className="destination-form">
                    <h2>Set destination for {selectedPerson.name}</h2>
                    <label>
                        Destination floor:
                        <input
                            type="number"
                            value={destinationFloor}
                            onChange={(e) => setDestinationFloor(e.target.value)}
                        />
                    </label>
                    <div>
                        Select direction:
                        <label>
                            <input
                                type="radio"
                                value="up"
                                checked={direction === 'up'}
                                onChange={() => setDirection('up')}
                            />
                            Up
                        </label>
                        <label>
                            <input
                                type="radio"
                                value="down"
                                checked={direction === 'down'}
                                onChange={() => setDirection('down')}
                            />
                            Down
                        </label>
                    </div>
                    <button type="submit">Set Destination</button>
                </form>
            )}
        </div>
    )
}

export default PersonMenu
