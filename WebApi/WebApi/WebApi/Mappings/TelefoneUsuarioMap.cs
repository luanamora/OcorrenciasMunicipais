using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class TelefoneUsuarioMap : IEntityTypeConfiguration<TelefoneUsuario>
    {
        public void Configure(EntityTypeBuilder<TelefoneUsuario> entity)
        {
            entity.HasKey(e => new { e.CdTelefone, e.CdUsuario });

            entity.ToTable("telefone_usuario");

            entity.Property(e => e.CdTelefone)
                .HasColumnName("cd_telefone")
                .ValueGeneratedOnAdd();

            entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

            entity.Property(e => e.DsTelefone)
                .IsRequired()
                .HasColumnName("ds_telefone")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.Property(e => e.NrDdd)
                .IsRequired()
                .HasColumnName("nr_ddd")
                .HasColumnType("character varying(5)");

            entity.Property(e => e.NrTelefone)
                .IsRequired()
                .HasColumnName("nr_telefone")
                .HasColumnType("character varying(20)");

            entity.HasOne(d => d.CdUsuarioNavigation)
                .WithMany(p => p.TelefoneUsuario)
                .HasForeignKey(d => d.CdUsuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("usuario_telefone_fk");
        }
    }
}
