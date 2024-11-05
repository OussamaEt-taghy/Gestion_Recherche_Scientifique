import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ForgotPasswordService } from './services/forgot-password.service';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-recover-pass-word',
  templateUrl: './recover-pass-word.component.html',
  styleUrls: ['./recover-pass-word.component.scss']
})
export class RecoverPassWordComponent implements OnInit {

  emailForm: FormGroup;
  otpForm: FormGroup;
  passwordForm: FormGroup;
  step: number = 1;

  email: string = '';
  hidePassword = true;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private forgotPasswordService: ForgotPasswordService,
    private spinner: NgxSpinnerService
  ) {
    this.emailForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });

    this.otpForm = this.fb.group({
      otp: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]]
    });

    this.passwordForm = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(8)]],
      repeatPassword: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  ngOnInit(): void {
    // Initialisation si nécessaire
  }

  openSpiner() {
    this.spinner.show();
    setTimeout(() => {
      this.spinner.hide();
    }, 5000);
  }

  // Étape 1: Envoi de l'OTP
  sendOtp() {
    if (this.emailForm.valid) {
      this.email = this.emailForm.value.email;
      this.openSpiner(); // Affichage du spinner
      this.forgotPasswordService.verifyMail(this.email).subscribe(
        () => {
          this.spinner.hide(); // Arrêter le spinner
          this.step = 2; // Passage à l'étape OTP
        },
        error => {
          this.spinner.hide(); // Arrêter le spinner en cas d'erreur
          alert('Erreur lors de l\'envoi de l\'OTP');
        }
      );
    }
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  // Étape 2: Vérification de l'OTP
  verifyOtp() {
    if (this.otpForm.valid) {
      const otp = this.otpForm.value.otp;
      this.openSpiner(); // Affichage du spinner
      this.forgotPasswordService.verifyOtp(otp, this.email).subscribe(
        () => {
          this.spinner.hide(); // Arrêter le spinner
          this.step = 3; // Passage à l'étape changement de mot de passe
        },
        error => {
          this.spinner.hide(); // Arrêter le spinner en cas d'erreur
          alert('OTP invalide');
        }
      );
    }
  }

  // Étape 3: Changement de mot de passe
  changePassword() {
    if (this.passwordForm.valid && this.passwordForm.value.password === this.passwordForm.value.repeatPassword) {
      const { password, repeatPassword } = this.passwordForm.value;
      this.openSpiner(); // Affichage du spinner
      this.forgotPasswordService.changePassword(this.email, password, repeatPassword).subscribe(
        () => {
          this.spinner.hide(); // Arrêter le spinner
          alert('Mot de passe modifié avec succès');
          // Rediriger vers la page de login après succès
          this.router.navigate(['/login']);
        },
        error => {
          this.spinner.hide(); // Arrêter le spinner en cas d'erreur
          alert('Erreur lors du changement de mot de passe');
        }
      );
    } else {
      alert('Les mots de passe ne correspondent pas');
    }
  }
}
