export const BASE_URL='http://localhost:8080'

export const cloudinaryUpload = async (pics) => {
    // Input validation (optional but recommended)
    if (!pics || !(pics instanceof File)) {
      throw new Error('Invalid file provided. Please select an image to upload.');
    }
  
    const formData = new FormData();
    formData.append('file', pics);
    formData.append('upload_preset', 'bnwuewiu'); // Replace with your actual Cloudinary preset
    formData.append('cloud_name', 'vishalxbhargav'); // Replace with your Cloudinary cloud name
  
    try {
      const response = await fetch(
        `https://api.cloudinary.com/v1_1/vishalxbhargav/image/upload`,
        {
          method: 'POST',
          body: formData,
        }
      );
  
      if (!response.ok) {
        throw new Error(`Cloudinary upload failed with status: ${response.status}`);
      }
  
      const data = await response.json();
      console.log('Cloudinary upload successful:', data);
      return data.url; // Return the uploaded image URL
    } catch (error) {
      console.error('Cloudinary upload error:', error);
      // Handle upload errors gracefully, e.g., display an error message to the user
      throw error; // Re-throw the error for handling in the calling function
    }
  };
  