import {ElevatorSimulator} from '../../components/Elevator/ElevatorSimulator'
import PersonMenu from '../../components/Persons/PersonMenu'
import './style.css'

export default function HomePage ()  {
  return (
    <>
      <div className="container">
        <ElevatorSimulator />
        <PersonMenu />
      </div>
    </>
  )
}
