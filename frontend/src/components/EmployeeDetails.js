import { useState } from "react";

const EmployeeDetails = ({onSubmit}) => {
     const [file,setFile]=useState(null)
     const handleSubmit=(e)=>{
          e.preventDefault();
         if(file!=null)  {
          onSubmit(file)
         }
     }
  return (
    <div className="main">

    <form action="#" className="systeem_form" encType="multipart/form-data" onSubmit={handleSubmit} >
    <div className="upload_file_input">
      <label className="label">
        <img src="/attach_file_add_.png" />
        <span className="title">Upload</span>
        <input type="file" id="avatar_img" required onChange={(e)=> setFile(e.target.files[0])} />
      </label>
    </div>
    <div className="buttons-group">
    <div>
      {file!==null ?( <button type="submit" id="btnrestart" >
           Process
      </button>):(<></>)}
     
    </div>
    </div>
  </form>
    </div>
  );
};

export default EmployeeDetails;
