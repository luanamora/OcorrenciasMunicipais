using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class UsuarioMap : IEntityTypeConfiguration<Usuario>
    {
        public void Configure(EntityTypeBuilder<Usuario> entity)
        {
            entity.HasKey(e => e.CdUsuario);

            entity.ToTable("usuario");

            entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

            entity.Property(e => e.DsEmail)
                .IsRequired()
                .HasColumnName("ds_email")
                .HasColumnType("character varying(80)");

            entity.Property(e => e.DsSenha)
                .IsRequired()
                .HasColumnName("ds_senha")
                .HasColumnType("character varying(30)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.Property(e => e.DtNascimento)
                .HasColumnName("dt_nascimento")
                .HasColumnType("date");

            entity.Property(e => e.NmUsuario)
                .IsRequired()
                .HasColumnName("nm_usuario")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.StAdministrador).HasColumnName("st_administrador");

            entity.Property(e => e.StStatus).HasColumnName("st_status");
        }
    }
}
