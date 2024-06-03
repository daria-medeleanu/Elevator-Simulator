import { useEffect, useState } from 'react';
import axios from 'axios';

export function ElevatorSimulator() {
  const [elevators, setElevators] = useState([]);

  useEffect(() => {
    const fetchElevators = async () => {
      try {
        const response = await axios.get('http://localhost:8082/elevators');
        setElevators(response.data);
      } catch (error) {
        console.error('Error fetching elevator data:', error);
      }
    };

    // fetchElevators();
    const intervalId = setInterval(fetchElevators, 1000);

    return () => clearInterval(intervalId);
  
  }, []);

  return (
    <div>
      <h1>Elevator Simulator</h1>
      <ul>
        {elevators.map((elevator) => (
          <li key={elevator.name}>{elevator.name} is at floor {elevator.currentFloor}</li>
        ))}
      </ul>
    </div>
  );
}