import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PeliculaService, Pelicula } from '../../services/pelicula';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-peliculas',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './peliculas.html',
  styleUrl: './peliculas.css'
})
export class PeliculasComponent implements OnInit {
  peliculas: Pelicula[] = [];
  busqueda = '';

  constructor(private peliculaService: PeliculaService, public auth: AuthService) {}

  ngOnInit() {
    this.listar();
  }

  listar() {
    this.peliculaService.listar().subscribe(p => this.peliculas = p);
  }

  buscar() {
    if (this.busqueda.trim()) {
      this.peliculaService.buscar(this.busqueda).subscribe(p => this.peliculas = p);
    } else {
      this.listar();
    }
  }
}
