import { Inter } from 'next/font/google'
import axios from 'axios'
const inter = Inter({ subsets: ['latin'] })
import EmployeeDetails from '../components/EmployeeDetails'
import Emplyees from '../components/Emplyees'
import Jobs from '../components/jobs'
import { useState } from 'react'
export default function Home() {
  const [data ,setData]=useState(null)
  const handleFileSubmit = async (file) => {
    try {
      const formData = new FormData();
      formData.append('file', file);

      const response = await axios.post('http://localhost:8080/process', formData);

      if (response.status === 200) {
        console.log('File uploaded successfully');
        setData(response.data.body)
      } else {
        console.error('Error uploading file to server');
      }
    } catch (error) {
      console.error('Network error:', error);
    }
  };

  return (
    <main  className='container'>
       <div>
        <EmployeeDetails onSubmit={handleFileSubmit}/>
        {data!=null ? ( <>
                    <Emplyees data={data.employees}/>
                   <Jobs data={data.jobSummaries}/></>
         ):(<></>)}
       </div>
    </main>
  )
}
