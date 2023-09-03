const tarjeta = document.querySelector('#tarjeta'),
                formulario = document.querySelector('#formulario-tarjeta'),
                numeroTarjeta = document.querySelector('#tarjeta .numero'),
                nombreTarjeta = document.querySelector('#tarjeta .nombre'),
                logoMarca = document.querySelector('#logo-marca'),
                firma = document.querySelector('#tarjeta .firma p'),
                mesExpiracion = document.querySelector('#tarjeta .mes'),
                yearExpiracion = document.querySelector('#tarjeta .year');
        ccv = document.querySelector('#tarjeta .ccv');

        // * Volteamos la tarjeta para mostrar el frente.
        const mostrarFrente = () => {
            if (tarjeta.classList.contains('active')) {
                tarjeta.classList.remove('active');
            }
        }

        // * Rotacion de la tarjeta
        tarjeta.addEventListener('click', () => {
            tarjeta.classList.toggle('active');
        });





        // * Input numero de tarjeta
        formulario.inputNumero.addEventListener('keyup', (e) => {
            let valorInput = e.target.value;

            formulario.inputNumero.value = valorInput
                    // Eliminamos espacios en blanco
                    .replace(/\s/g, '')
                    // Eliminar las letras
                    .replace(/\D/g, '')
                    // Ponemos espacio cada cuatro numeros
                    .replace(/([0-9]{4})/g, '$1 ')
                    // Elimina el ultimo espaciado
                    .trim();

            numeroTarjeta.textContent = valorInput;

            if (valorInput == '') {
                numeroTarjeta.textContent = '#### #### #### ####';

                logoMarca.innerHTML = '';
            }

            if (valorInput[0] == 4) {
                logoMarca.innerHTML = '';
                const imagen = document.createElement('img');
                imagen.src = './wwwroot/image/visa.png';
                logoMarca.appendChild(imagen);
            } else if (valorInput[0] == 5) {
                logoMarca.innerHTML = '';
                const imagen = document.createElement('img');
                imagen.src = './wwwroot/image/master.png';
                logoMarca.appendChild(imagen);
            }

            // Volteamos la tarjeta para que el usuario vea el frente.
            mostrarFrente();
        });

        // * Input nombre de tarjeta
        formulario.inputNombre.addEventListener('keyup', (e) => {
            let valorInput = e.target.value;

            formulario.inputNombre.value = valorInput.replace(/[0-9]/g, '');
            nombreTarjeta.textContent = valorInput;
            firma.textContent = valorInput;

            if (valorInput == '') {
                nombreTarjeta.textContent = 'ShopShoes';
            }

            mostrarFrente();
        });

        formulario.monthS.addEventListener('change', (e) => {
            yearExpiracion.textContent = e.target.value;
            mostrarFrente();
        });


        formulario.inputCCV.addEventListener('keyup', () => {
            if (!tarjeta.classList.contains('active')) {
                tarjeta.classList.toggle('active');
            }

            formulario.inputCCV.value = formulario.inputCCV.value
                    // Eliminar los espacios
                    .replace(/\s/g, '')
                    // Eliminar las letras
                    .replace(/\D/g, '');

            ccv.textContent = formulario.inputCCV.value;
        });




