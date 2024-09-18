// Function to send message
function sendMessage() {
    const message = document.getElementById("messageInput").value;

    if (message.trim() === "") {
        alert("Please enter a message.");
        return;
    }

    const messageData = {
        content: message
    };

    fetch('/api/messages/send', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(messageData),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Message sent successfully!");
            document.getElementById("messageInput").value = ""; // Clear input
        } else {
            alert("Failed to send message.");
        }
    })
    .catch(error => console.error('Error:', error));
}

// Function to retrieve lat est messages
function retrieveMessages() {
    fetch('/api/messages/retrieve')
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {
            console.log("Retrieved messages:", data); // Check the response data in the console

            // Extract messages from the `data` field in the API response
            const messages = data.data;  // Access the messages in the `data` field

            const tableBody = document.getElementById("tableBody");
            tableBody.innerHTML = ''; // Clear existing table rows

            if (messages.length === 0) {
                alert("No messages to display.");
                return;
            }

            // Loop through the messages and populate the table
            messages.forEach(item => {
                const row = document.createElement("tr");

                const messageCell = document.createElement("td");
                messageCell.textContent = item.content;
                row.appendChild(messageCell);

                const timestampCell = document.createElement("td");
                timestampCell.textContent = item.timestamp;
                row.appendChild(timestampCell);

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching messages:', error);
            alert("Failed to retrieve messages. Check console for details.");
        });
}
