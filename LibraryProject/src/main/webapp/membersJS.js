const servletUrl = '/members/';

async function loadMembers() {
    try {
        const response = await fetch(servletUrl);

        if (!response.ok) throw new Error('Network response was not ok');
        const members = await response.json();

        const memberList = document.querySelector('#membersDiv ul');
        members.forEach(member => {
            const li = document.createElement('li');
            li.textContent = `${member.name} (${member.email})`;

            memberList.appendChild(li);
        });

    } catch (error) {
        console.error('Error fetching members:', error);
    }
}

loadMembers();