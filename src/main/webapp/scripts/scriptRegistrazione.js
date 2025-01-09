document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registrationForm');

    // Funzione per pre-caricare l'immagine di sfondo
    const preloadBackground = (url) => {
        const img = new Image();
        img.src = url;
        img.onload = () => {
            document.querySelector('.registration-container').style.backgroundImage = `url('${url}')`;
            document.querySelector('.registration-container').classList.add('loaded');
        };
    };

    // Pre-carica l'immagine di sfondo (aggiungi il percorso completo della tua immagine)
    preloadBackground('images/sfondologin.gif');

    // Funzione per validare il form
    const validateForm = () => {
        const email = document.getElementById('email').value.trim();
        const nome = document.getElementById('nome').value.trim();
        const cognome = document.getElementById('cognome').value.trim();
        const password = document.getElementById('password').value.trim();

        // Validazione email usando espressione regolare semplice
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert('Inserisci un indirizzo email valido.');
            return false;
        }

        // Validazione password: almeno 8 caratteri, almeno una lettera maiuscola, almeno un numero
        const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;
        if (!passwordRegex.test(password)) {
            alert('La password deve contenere almeno 8 caratteri, una lettera maiuscola e un numero.');
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
