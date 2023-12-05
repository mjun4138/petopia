import logo from './logo.svg';
// import './App.css';
// import { DatePicker } from 'antd';
import {Button} from "antd";
const App = () => (
  <div className='btn'>
    <Button type="primary">Primary Button</Button>
    <Button>Default Button</Button>
    <Button type="dashed">Dashed Button</Button>
    <Button type="text">Text Button</Button>
    <Button type="link">Link Button</Button>
  </div>
);

export default App;
