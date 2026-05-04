import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Pelicula {
  id?: number;
  nombre: string;
  descripcion: string;
  imagenUrl: string;
  fechaAgregado?: string;
}

@Injectable({
  providedIn: 'root'
})
export class PeliculaService {

  private url = environment.peliculasApi + '/peliculas';

  constructor(private http: HttpClient) {}

  listar(): Observable<Pelicula[]> {
    return this.http.get<Pelicula[]>(this.url);
  }

  buscar(nombre: string): Observable<Pelicula[]> {
    return this.http.get<Pelicula[]>(`${this.url}/buscar?nombre=${nombre}`);
  }

  agregar(p: Pelicula): Observable<Pelicula> {
    return this.http.post<Pelicula>(this.url, p);
  }

  editar(id: number, p: Pelicula): Observable<Pelicula> {
    return this.http.put<Pelicula>(`${this.url}/${id}`, p);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
