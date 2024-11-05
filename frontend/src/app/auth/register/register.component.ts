import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../Service/auth.service'; 
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  hidePassword = true;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService, // Injection du service AuthService
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      fullname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const signUpDTORequest = {
        fullname: this.fullname?.value,
        email: this.email?.value,
        password: this.password?.value
      };

      // Appel du service d'inscription
      this.authService.signUp(signUpDTORequest).subscribe(
        (response) => {
          console.log('Inscription réussie', response);
          // Afficher un message de succès et rediriger vers la page de login
          this.snackBar.open('Inscription réussie', 'Fermer', { duration: 5000 });
          this.router.navigate(['/auth/login']);
        },
        (error) => {
          console.error('Erreur lors de l\'inscription', error);
          // Afficher un message d'erreur
          this.snackBar.open('Erreur lors de l\'inscription', 'Fermer', { duration: 5000 });
        }
      );
    }
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  get fullname() { return this.registerForm.get('fullname'); }
  get email() { return this.registerForm.get('email'); }
  get password() { return this.registerForm.get('password'); }
}
