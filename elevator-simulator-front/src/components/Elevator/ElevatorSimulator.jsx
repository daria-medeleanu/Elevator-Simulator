import { useEffect, useState } from 'react';
import axios from 'axios';
import './ElevatorSimulator.css'

export function ElevatorSimulator() {
  const [elevators, setElevators] = useState([]);

  useEffect(() => {
    const fetchElevators = async () => {
      try {
        const response = await axios.get('http://localhost:8082/elevators');
        setElevators(response.data);
        console.log(response.data);
      } catch (error) {
        console.error('Error fetching elevator data:', error);
      }
    };

    // fetchElevators();
    const intervalId = setInterval(fetchElevators, 1000);

    return () => clearInterval(intervalId);
  
  }, []);

  const floorHeight =50;

  return (
    <div className="elevator-simulator">
      <div>
        <h1>Elevator Simulator</h1>
        <ul>
         {elevators.map((elevator) => (
<<<<<<< HEAD
           <li key={elevator.name}>{elevator.name} is at floor {elevator.currentFloor} and has requests at: {elevator.floorRequests}</li>
=======
           <li key={elevator.name}>{elevator.name} is at floor {elevator.currentFloor} and has requests at{elevator.floorRequests}</li>
>>>>>>> e82bbac240a9cad05b6c73d1c8cfc17a6c799507
         ))}
        </ul>
      </div>
      <div className="building">
        {[...Array(10)].map((_,i) => (
          <div key={i} className="floor" style={{ bottom: `${i * floorHeight}px` }}>
            Floor {i}
          </div>
        ))}
        {elevators.map((elevator, index) => (
          <div
            key={elevator.name}
            className={`elevator elevator-${index + 1}`}
            style={{transform: `translateY(${(9-elevator.currentFloor) * floorHeight}px)`}}
          >
            {elevator.name}
          </div>
        ))}
      </div>
    </div>
  );
}