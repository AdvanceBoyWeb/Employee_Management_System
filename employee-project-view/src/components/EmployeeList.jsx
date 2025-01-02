import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import EmployeeService from '../service/EmployeeService';

const EmployeeList = () => {

  const [loading, setLoading] = useState(true);
  const [employees, setEmployees] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await EmployeeService.getEmployees();
        setEmployees(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };
    fetchData();
  }, []);


  const deletEmployee = (e, id) => {
    e.preventDefault();
    EmployeeService.deleteEmployeeById(id)
      .then(() => {
        if (employees) {
          setEmployees((prevElement) => {
            return prevElement.filter((employee) => employee.id !== id);
          })
        }
      })
  };

  const editEmployee = (e, id) => {
    e.preventDefault();
    navigate(`/editEmployee/${id}`)
  }

  const navigate = useNavigate();
  return (

    <div className='container mx-auto my-8'>
      <div>
        <button onClick={() => navigate("/addEmployee")}
          className='bg-slate-600 hover:bg-blue-700  my-12 font-semibold px-20 py-2 rounded'> Add 👨‍💻 Employee </button>
      </div>

      <table className='shadow'>
        <thead className='bg-slate-600'>
          <th className='px-6 py-3 tracking-wide'>Name</th>
          <th className='px-6 py-3 tracking-wide'>Phone</th>
          <th className='px-6 py-3 tracking-wide'>Email</th>
          <th className='px-6 py-3 tracking-wide'>Action</th>
        </thead>

        {!loading && (
          <tbody>
            {employees.map((employee) => (
              <tr className=' text-slate-500 hover:bg-white hover:text-black'>
                <td className='text-left px-6 py-4 whitesapce-nowrape'>{employee.name}</td>
                <td className='text-left px-6 py-4 whitesapce-nowrape'>{employee.phone}</td>
                <td className='text-left px-6 py-4 whitesapce-nowrape'>{employee.email}</td>
                <td className='text-left px-6 py-4 whitesapce-nowrape'>
                  <a onClick={(e, id) => editEmployee(e, employee.id)}
                    className='hover:text-green-500 hover:cursor-pointer mx-3'
                  >Edit 📝</a>
                  <a onClick={(e, id) => deletEmployee(e, employee.id)}
                    className='hover:text-red-500 hover:cursor-pointer'
                  >Delete 🗑️</a>
                </td>
              </tr>
            ))}
          </tbody>
        )}
      </table>
    </div>
  )
}

export default EmployeeList