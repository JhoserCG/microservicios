import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { PeliculaService, Pelicula } from '../../services/pelicula';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class AdminComponent implements OnInit {
  peliculas: Pelicula[] = [];
  nueva: Pelicula = { nombre: '', descripcion: '', imagenUrl: '' };
  editando: Pelicula | null = null;

  constructor(private peliculaService: PeliculaService) {}

  ngOnInit() {
    this.listar();
  }

  listar() {
    this.peliculaService.listar().subscribe(p => this.peliculas = p);
  }

  agregar() {
    this.peliculaService.agregar(this.nueva).subscribe(() => {
      this.nueva = { nombre: '', descripcion: '', imagenUrl: '' };
      this.listar();
    });
  }

  editar(p: Pelicula) {
    this.editando = { ...p };
  }

  guardarEdicion() {
    if (this.editando?.id) {
      this.peliculaService.editar(this.editando.id, this.editando).subscribe(() => {
        this.editando = null;
        this.listar();
      });
    }
  }

  eliminar(id: number) {
    if (confirm('¿Eliminar esta película?')) {
      this.peliculaService.eliminar(id).subscribe(() => this.listar());
    }
  }
}
