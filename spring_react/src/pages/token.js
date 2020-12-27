

const cookieValue = document.cookie
    .split('; ')
    .find(row => row.startsWith('Bearer'))
    .split('=')[1];