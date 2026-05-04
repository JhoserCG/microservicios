import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Usuario {
  id?: number;
  nombre: string;
  email: string;
  password: string;
  rol?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = environment.usuariosApi + '/auth';

  constructor(private http: HttpClient) {}

  registrar(u: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.url}/registro`, u);
  }

  login(email: string, password: string): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.url}/login`, { email, password });
  }

  guardarUsuario(u: Usuario) {
    localStorage.setItem('usuario', JSON.stringify(u));
  }

  obtenerUsuario(): Usuario | null {
    const u = localStorage.getItem('usuario');
    return u ? JSON.parse(u) : null;
  }

  cerrarSesion() {
    localStorage.removeItem('usuario');
  }

  estaLogueado(): boolean {
    return !!localStorage.getItem('usuario');
  }

  esAdmin(): boolean {
    const u = this.obtenerUsuario();
    return u?.rol === 'ADMIN';
  }
}
