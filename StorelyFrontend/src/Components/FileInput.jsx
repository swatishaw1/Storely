import axios from "axios";
import { useRef, useState } from "react";
import toast from "react-hot-toast";

const FileInput = () => {
    const fileRef = useRef(null);
    const [files, setFiles] = useState([]);
    const MAX_FILES = 8;
    const MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    const handleFileChange = (e) => {
        const selectedFiles = Array.from(e.target.files);
        setFiles((prevFiles) => {
            const total = prevFiles.length + selectedFiles.length;
            const totalSize = prevFiles.size + selectedFiles.size;
            if (totalSize > MAX_FILE_SIZE) {
                alert(`File "${selectedFiles.name}" exceeds 10MB limit`);
                return prevFiles;
            }
            if (total >= MAX_FILES) {
                alert(`${MAX_FILES} files limit reached please refresh the page`);
                return prevFiles; // reject new files
            }
            return [...prevFiles, ...selectedFiles];
        });
    }
    const handleUpload = async () => {
        if (files.length === 0) {
            toast.error("Select files first");
            return;
        }
        try {
            const formData = new FormData();
            [...files].forEach(file => formData.append("files", file));
            const response = await axios.post("http://localhost:8080/files/uploads", formData);
            if (response) {
                toast.success("Files Successfully Upload");
            }
        } catch (err) {
            console.log(err);
            toast.error(err.response?.data?.message || "Upload failed");

        }
    }
    return (
        <div className="m-5">
            <div className="flex gap-5">
                <input type="file" ref={fileRef} multiple className="hidden" onChange={handleFileChange} />
                <button onClick={() => fileRef.current.click()} className="bg-gray-300 border-2 border-black px-4 py-2 rounded font-medium"
                >+ Export Files
                </button>
                <button className="m-2 bg-black text-white text-center text-xs p-2 rounded" onClick={handleUpload}>Upload</button>
            </div>
            <div>
                {files.length > 0 && files.length < MAX_FILES && (
                    <ul className="text-sm text-gray-700 mt-2">
                        {files.map((file, idx) => (
                            <li key={idx}>{file.name}</li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
}

export default FileInput;