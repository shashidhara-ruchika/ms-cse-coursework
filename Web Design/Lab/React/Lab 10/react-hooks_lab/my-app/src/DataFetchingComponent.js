// Sample code for data fetching

import React, { useState, useEffect } from 'react';

function DataFetchingComponent() {
    const [data, setData] = useState([]); // State to store the fetched data
    const [isLoading, setIsLoading] = useState(true); // State to track loading status

    useEffect(() => {
        // Define the async function for fetching data
        const fetchData = async () => {
            try {
                const response = await fetch('https://api.example.com/data');
                const jsonData = await response.json();
                setData(jsonData); // Update the state with the fetched data
                setIsLoading(false); // Set loading to false after data is fetched
            } catch (error) {
                console.error('Error fetching data: ', error);
                setIsLoading(false); // Set loading to false even if there is an error
            }
        };

        fetchData(); // Call the function to fetch data
    }, []); // Empty dependency array means this effect runs once after the initial render

    // Render the component
    return (
        <div>
            {isLoading ? (
                <p>Loading...</p>
            ) : (
                <ul>
                    {data.map(item => (
                        <li key={item.id}>{item.title}</li> // Assuming each item has 'id' and 'title'
                    ))}
                </ul>
            )}
        </div>
    );
}

export default DataFetchingComponent;
