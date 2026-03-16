const servletUrl = '/books/'; 

// 2. Fetch the data when the page loads
async function loadBooks() {
    try {
        const response = await fetch(servletUrl);
        
        // Ensure the response is okay (status 200)
        if (!response.ok) throw new Error('Network response was not ok');
        
        // Parse the JSON data
        const books = await response.json();
        
        // 3. Target the <ul> inside your booksDiv
        const bookList = document.querySelector('#booksDiv ul');
        
        // Clear existing static "Book 1", "Book 2" placeholders
        bookList.innerHTML = '';

        // 4. Loop through the JSON array and create <li> elements
        books.forEach(book => {
            const li = document.createElement('li');
            
            // Assuming your Java Object/JSON has 'title' and 'author' fields
            li.textContent = `${book.title} by ${book.author} (Code: ${book.code})`;
            
            // Append to the list
            bookList.appendChild(li);
        });

    } catch (error) {
        console.error('Error fetching books:', error);
    }
}

// Call the function
loadBooks();