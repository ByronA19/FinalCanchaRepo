"use strict";

// Class Definition
var KTLogin = function () {
    var _buttonSpinnerClasses = 'spinner spinner-right spinner-white pr-15';

    var _handleFormSignin = function () {
        var form = KTUtil.getById('kt_login_singin_form');
        var formSubmitUrl = KTUtil.attr(form, 'action');
        var formSubmitButton = KTUtil.getById('kt_login_singin_form_submit_button');

        if (!form) {
            return;
        }

        FormValidation
                .formValidation(
                        form,
                        {
                            fields: {
                                email: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Ingresa el email'
                                        }
                                    }
                                },
                                password: {
                                    validators: {
                                        notEmpty: {
                                            message: 'La contraseña es requerida'
                                        }
                                    }
                                }
                            },
                            plugins: {
                                trigger: new FormValidation.plugins.Trigger(),
                                submitButton: new FormValidation.plugins.SubmitButton(),
                                //defaultSubmit: new FormValidation.plugins.DefaultSubmit(), // Uncomment this line to enable normal button submit after form validation
                                bootstrap: new FormValidation.plugins.Bootstrap({
                                    //	eleInvalidClass: '', // Repace with uncomment to hide bootstrap validation icons
                                    //	eleValidClass: '',   // Repace with uncomment to hide bootstrap validation icons
                                })
                            }
                        }
                )
                .on('core.form.valid', function () {
                    
                    form.submit();
                    
                    // Show loading state on button
//                    KTUtil.btnWait(formSubmitButton, _buttonSpinnerClasses, "Please wait");

                    // Simulate Ajax request
//                    setTimeout(function () {
//                        KTUtil.btnRelease(formSubmitButton);
//                    }, 2000);

                    // Form Validation & Ajax Submission: https://formvalidation.io/guide/examples/using-ajax-to-submit-the-form
                    
//                    FormValidation.utils.fetch(formSubmitUrl, {
//                        method: 'POST',
//                        dataType: 'json',
//                        params: {
//                            name: form.querySelector('[name="username"]').value,
//                            email: form.querySelector('[name="password"]').value,
//                        },
//                    }).then(function (response) { // Return valid JSON
//                        // Release button
//                        KTUtil.btnRelease(formSubmitButton);
//
//                        if (response && typeof response === 'object' && response.status && response.status == 'success') {
//                            Swal.fire({
//                                text: "All is cool! Now you submit this form",
//                                icon: "success",
//                                buttonsStyling: false,
//                                confirmButtonText: "Ok, got it!",
//                                customClass: {
//                                    confirmButton: "btn font-weight-bold btn-light-primary"
//                                }
//                            }).then(function () {
//                                KTUtil.scrollTop();
//                            });
//                        } else {
//                            Swal.fire({
//                                text: "Sorry, something went wrong, please try again.",
//                                icon: "error",
//                                buttonsStyling: false,
//                                confirmButtonText: "Ok, got it!",
//                                customClass: {
//                                    confirmButton: "btn font-weight-bold btn-light-primary"
//                                }
//                            }).then(function () {
//                                KTUtil.scrollTop();
//                            });
//                        }
//                    });
                     
                })
                .on('core.form.invalid', function () {
                    Swal.fire({
                        text: "Por favor, debes ingresar nombre de usuario y contraseña",
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, de acuerdo!",
                        customClass: {
                            confirmButton: "btn font-weight-bold btn-light-primary"
                        }
                    }).then(function () {
                        KTUtil.scrollTop();
                    });
                });
    }
    
    
    const validarCedula = function (a) {
        return {
            validate: function (input) {
                
                const value = input.value;
                if (value === '') {
                    return {
                        valid: false
                    };
                }

                // Check the password strength
                if (value.length !== 10) {
                    return {
                        valid: false
                    };
                }
                
                var total = 0;
                var cad = value;
                var longitud = cad.length;
                var longcheck = longitud - 1;      
                
                if (cad !== "" && longitud === 10) {
                    for (var i = 0; i < longcheck; i++) {
                        if (i % 2 === 0) {
                            var aux = cad.charAt(i) * 2;
                            if (aux > 9)
                                aux -= 9;
                            total += aux;
                        } else {
                            total += parseInt(cad.charAt(i)); // parseInt o concatenará en lugar de sumar
                        }
                    }
                    total = total % 10 ? 10 - total % 10 : 0;
                    if (cad.charAt(longitud - 1) == total) {
                        return {
                            valid: true
                        };
                    }
                }
                
                return {
                    valid: false
                };
            },
        };
    };

    // Register the validator
    FormValidation.validators.cedulaValida = validarCedula;    

    var _handleFormForgot = function () {
        var form = KTUtil.getById('kt_login_forgot_form');
        var formSubmitUrl = KTUtil.attr(form, 'action');
        var formSubmitButton = KTUtil.getById('kt_login_forgot_form_submit_button');

        if (!form) {
            return;
        }

        FormValidation
                .formValidation(
                        form,
                        {
                            fields: {
                                cedula: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Ingresa la cédula'
                                        },
                                        digits: {
                                            message: 'Solamente números'
                                        },
                                        cedulaValida: {
                                            message: 'La cédula no es válida'
                                        },
                                    }
                                },
                                email: {
                                    validators: {
                                        notEmpty: {
                                            message: 'El email es requerido'
                                        },
                                        emailAddress: {
                                            message: 'Email no válido'
                                        }
                                    }
                                },
                                password: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Ingresa la nueva contraseña'
                                        },
                                    }
                                },
                            },
                            plugins: {
                                trigger: new FormValidation.plugins.Trigger(),
                                submitButton: new FormValidation.plugins.SubmitButton(),
                                defaultSubmit: new FormValidation.plugins.DefaultSubmit(), // Uncomment this line to enable normal button submit after form validation
                                bootstrap: new FormValidation.plugins.Bootstrap({
                                    //	eleInvalidClass: '', // Repace with uncomment to hide bootstrap validation icons
                                    //	eleValidClass: '',   // Repace with uncomment to hide bootstrap validation icons
                                })
                            }
                        }
                )
                .on('core.form.valid', function () {
                    // Show loading state on button
                    KTUtil.btnWait(formSubmitButton, _buttonSpinnerClasses, "Please wait");

                    // Simulate Ajax request
                    setTimeout(function () {
                        KTUtil.btnRelease(formSubmitButton);
                    }, 2000);
                })
                .on('core.form.invalid', function () {
                    Swal.fire({
                        text: "Por favor, llena todos los campos",
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, de acuerdo!",
                        customClass: {
                            confirmButton: "btn font-weight-bold btn-light-primary"
                        }
                    }).then(function () {
                        KTUtil.scrollTop();
                    });
                });
    }

    var _handleFormSignup = function () {
        // Base elements
        var wizardEl = KTUtil.getById('kt_login');
        var form = KTUtil.getById('kt_login_signup_form');
        var wizardObj;
        var validations = [];

        if (!form) {
            return;
        }

        // Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
        // Step 1
        validations.push(FormValidation.formValidation(
                form,
                {
                    fields: {
                        nombre: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                }
                            }
                        },
                        apellidos: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                }
                            }
                        },
                        telefono: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                },
                                digits: {
                                    message: 'Solamente números'
                                }
                            }
                        },
                        celular: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                },
                                digits: {
                                    message: 'Solamente números'
                                }
                            }
                        },
                    },
                    plugins: {
                        trigger: new FormValidation.plugins.Trigger(),
                        bootstrap: new FormValidation.plugins.Bootstrap()
                    }
                }
        ));

        // Step 2
        validations.push(FormValidation.formValidation(
                form,
                {
                    fields: {
                        cedula: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                },
                                digits: {
                                    message: 'Solamente números'
                                },
                                cedulaValida: {
                                    message: 'La cédula no es válida'
                                },
                            }
                        },
                        direccion: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                }
                            }
                        },
                    },
                    plugins: {
                        trigger: new FormValidation.plugins.Trigger(),
                        bootstrap: new FormValidation.plugins.Bootstrap(),
                    }
                }
        ));

        // Step 3
        validations.push(FormValidation.formValidation(
                form,
                {
                    fields: {
//                        username: {
//                            validators: {
//                                notEmpty: {
//                                    message: 'Campo requerido'
//                                }
//                            }
//                        },
                        email: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                },
                                emailAddress: {
                                    message: 'Email no válido'
                                }
                            }
                        },
                        password: {
                            validators: {
                                notEmpty: {
                                    message: 'Campo requerido'
                                },
                                stringLength: {
                                    max: 10,
                                    message: '10 caracteres máximo'
                                }
                            }
                        }
                    },
                    plugins: {
                        trigger: new FormValidation.plugins.Trigger(),
                        submitButton: new FormValidation.plugins.SubmitButton(),
                        defaultSubmit: new FormValidation.plugins.DefaultSubmit(), // Uncomment this line to enable normal button submit after form validation>
                        bootstrap: new FormValidation.plugins.Bootstrap()
                    }
                }
        ));

        // Step 4
//        validations.push(FormValidation.formValidation(
//                form,
//                {
//                    fields: {
//                        ccname: {
//                            validators: {
//                                notEmpty: {
//                                    message: 'Credit card name is required'
//                                }
//                            }
//                        },
//                        ccnumber: {
//                            validators: {
//                                notEmpty: {
//                                    message: 'Credit card number is required'
//                                },
//                                creditCard: {
//                                    message: 'The credit card number is not valid'
//                                }
//                            }
//                        },
//                        ccmonth: {
//                            validators: {
//                                notEmpty: {
//                                    message: 'Credit card month is required'
//                                }
//                            }
//                        },
//                        ccyear: {
//                            validators: {
//                                notEmpty: {
//                                    message: 'Credit card year is required'
//                                }
//                            }
//                        },
//                        cccvv: {
//                            validators: {
//                                notEmpty: {
//                                    message: 'Credit card CVV is required'
//                                },
//                                digits: {
//                                    message: 'The CVV value is not valid. Only numbers is allowed'
//                                }
//                            }
//                        }
//                    },
//                    plugins: {
//                        trigger: new FormValidation.plugins.Trigger(),
//                        bootstrap: new FormValidation.plugins.Bootstrap()
//                    }
//                }
//        ));

        // Initialize form wizard
        wizardObj = new KTWizard(wizardEl, {
            startStep: 1, // initial active step number
            clickableSteps: false // to make steps clickable this set value true and add data-wizard-clickable="true" in HTML for class="wizard" element
        });

        // Validation before going to next page
        wizardObj.on('beforeNext', function (wizard) {
            
            validations[wizard.getStep() - 1].validate().then(function (status) {
                
//                if ( wizard.getStep() === 2 ) {
//                    $(".form-control").addClass("is-valid");
//                }
                
                if (status == 'Valid') {
                    wizardObj.goNext();
                    KTUtil.scrollTop();
                } else {
                    Swal.fire({
                        text: "Por favor, completa los campos",
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, de acuerdo!",
                        customClass: {
                            confirmButton: "btn font-weight-bold btn-light-primary"
                        }
                    }).then(function () {
                        KTUtil.scrollTop();
                    });
                }
            });

            wizardObj.stop();  // Don't go to the next step
        });

        // Change event
        wizardObj.on('change', function (wizard) {
            KTUtil.scrollTop();
        });
    }

    // Public Functions
    return {
        init: function () {
            _handleFormSignin();
            _handleFormForgot();
            _handleFormSignup();
        }
    };
}();

// Class Initialization
jQuery(document).ready(function () {
    KTLogin.init();
});
