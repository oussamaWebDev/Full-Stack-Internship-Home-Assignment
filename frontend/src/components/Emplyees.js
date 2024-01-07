import React, { useState } from 'react';
import { Table, Pagination } from 'react-bootstrap';

const PaginatedEmplyees = ({ data, itemsPerPage }) => {
  const [currentPage, setCurrentPage] = useState(1);

  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentData = data.slice(startIndex, endIndex);

  const totalPages = Math.ceil(data.length / itemsPerPage);

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <div className='container mx-4'>
      <Table responsive className='table table-dark' style={{ width: '80%' }}>
        <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Emplyee Name</th>
            <th scope="col">Job Title</th>
            <th scope="col">Salary</th>
          </tr>
        </thead>
        <tbody>
          {currentData.map((employee) => (
            <tr key={employee.id}>
              <th scope="row">{employee.id}</th>
              <td>{employee.employeeName}</td>
              <td>{employee.jobTitle}</td>
              <td>{employee.salary}</td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Pagination className="custom-pagination">
          <Pagination.First onClick={() => handlePageChange(1)} />
          <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 1} />

          {[...Array(Math.min(5, totalPages))].map((_, index) => {
          const page = currentPage - 2 + index;
          return (
               page > 0 && page <= totalPages && (
               <Pagination.Item
                    key={page}
                    active={page === currentPage}
                    onClick={() => handlePageChange(page)}
                    className="custom-pagination-item"
               >
                    {page}
               </Pagination.Item>
               )
          );
          })}

          <Pagination.Next onClick={() => handlePageChange(currentPage + 1)} disabled={currentPage === totalPages} />
          <Pagination.Last onClick={() => handlePageChange(totalPages)} />
    </Pagination>


    </div>
  );
};

const Emplyees = ({ data }) => {
  // number of elmment for eash page 
  const itemsPerPage = 10;

  return <div className='container mt-3 text-center' >
     <p className="h1">Employee information</p>
     <PaginatedEmplyees data={data} itemsPerPage={itemsPerPage} />
  </div>;
};

export default Emplyees;
