document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');

    // Funzione per pre-caricare l'immagine di sfondo
    const preloadBackground = (url) => {
        const img = new Image();
        img.src = url;
        img.onload = () => {
            document.querySelector('.login-container').style.backgroundImage = `url('${url}')`;
            document.querySelector('.login-container').classList.add('loaded');
        };
    };

    // Pre-carica l'immagine di sfondo
    preloadBackground('images/sfondologin.gif');

    // Funzione per validare il form
    const validateForm = () => {
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();
        if (email === '' || password === '') {
            alert('Per favore, riempi entrambi i campi.');
            return false;
        }
        return true;
    };

    // Aggiungi un gestore di eventi per il submit del form
    form.addEventListener('submit', (event) => {
        if (!validateForm()) {
            event.preventDefault(); // Impedisce l'invio del form se la validazione fallisce
        }
    });
});
