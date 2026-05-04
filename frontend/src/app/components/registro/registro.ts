import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './registro.html',
  styleUrl: './registro.css'
})
export class RegistroComponent {
  nombre = '';
  email = '';
  password = '';
  error = '';
  exito = '';

  constructor(private auth: AuthService, private router: Router) {}

  registrar() {
    this.auth.registrar({ nombre: this.nombre, email: this.email, password: this.password }).subscribe({
      next: () => {
        this.exito = '¡Registro exitoso! Ahora puedes iniciar sesión.';
        setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: () => this.error = 'Este correo ya está registrado.'
    });
  }
}
