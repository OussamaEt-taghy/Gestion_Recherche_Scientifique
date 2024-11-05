import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../Service/auth.service';
import { UserStorageService } from '../Service/user-storage.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  loginForm!: FormGroup;
  hidePassword = true;

  constructor(
   
    private fb: FormBuilder,
    private authService: AuthService,
    private userStorageService: UserStorageService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

 
  ngOnInit(): void {
    
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
   
    if (this.loginForm.valid) {
      
      const email = this.loginForm.get('email')!.value;
      const password = this.loginForm.get('password')!.value;
  
      
      this.authService.signin(email, password).subscribe(
        (res: any) => {
          console.log('Login API response:', res);
  
          
          if (res && res.token) {
          
            this.userStorageService.saveToken(res.token);
          
            
            const userRole = res.role;
            const userFullname = res.fullname;
          
            if (userFullname) {
              console.log('Redirection de l\'utilisateur :', userFullname);
          
              this.userStorageService.saveUser({ fullname: userFullname, role: userRole });
          
              
              if (userRole === 'Author') {
                this.router.navigate(['/author', encodeURIComponent(userFullname)]);
              } else if (userRole === 'Editor') {
                this.router.navigate(['/editor', encodeURIComponent(userFullname)]);
              } else if (userRole === 'Evaluator') {
                this.router.navigate(['/evaluator', encodeURIComponent(userFullname)]);
              }
            } else {
            
              console.warn('Le nom complet est indéfini ou nul :', userFullname);
              this.snackBar.open('Nom d\'utilisateur manquant', 'Fermer', { duration: 5000 });
            }
          }
        },
        (error: any) => {
          
          console.error('Login API error:', error);
          this.snackBar.open('Connexion échoué , verifier votre email ou mot de pass', 'Fermer', { duration: 5000 });
        }
      );
    }
  }

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  get email() { return this.loginForm.get('email'); }
  get password() { return this.loginForm.get('password'); }
}
