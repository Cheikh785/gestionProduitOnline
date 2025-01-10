package org.example.gestionproduitonline.web.rest.vm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterVM {
    @NotBlank(message = "Email ne doit pas être null")
    @Email(message = "Format email invalide")
    @Size(min = 3, max = 254, message = "L'email doit être compris entre 3 et  254 caractères")
    private String email;

    @NotBlank(message = "Le mot de passe ne doit pas être null")
    @Size(min = 8, max = 254, message = "Le mot de passe doit être compris entre 8 et 254 caractères")
    private String password;

    @NotBlank(message = "Le nom ne doit pas être null")
    private String name;

    public @NotBlank(message = "Email ne doit pas être null") @Email(message = "Format email invalide") @Size(min = 3, max = 254, message = "L'email doit être compris entre 3 et  254 caractères") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email ne doit pas être null") @Email(message = "Format email invalide") @Size(min = 3, max = 254, message = "L'email doit être compris entre 3 et  254 caractères") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Le mot de passe ne doit pas être null") @Size(min = 8, max = 254, message = "Le mot de passe doit être compris entre 8 et 254 caractères") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Le mot de passe ne doit pas être null") @Size(min = 8, max = 254, message = "Le mot de passe doit être compris entre 8 et 254 caractères") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Le nom ne doit pas être null") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Le nom ne doit pas être null") String name) {
        this.name = name;
    }
}
