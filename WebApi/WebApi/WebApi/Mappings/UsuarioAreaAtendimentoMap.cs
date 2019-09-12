using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class UsuarioAreaAtendimentoMap : IEntityTypeConfiguration<UsuarioAreaatendimento>
    {
        public void Configure(EntityTypeBuilder<UsuarioAreaatendimento> entity)
        {
            entity.HasKey(e => new { e.CdUsuarioatendimento, e.CdAreaatendimento });

            entity.ToTable("usuario_areaatendimento");

            entity.Property(e => e.CdUsuarioatendimento)
                .HasColumnName("cd_usuarioatendimento")
                .ValueGeneratedOnAdd();

            entity.Property(e => e.CdAreaatendimento).HasColumnName("cd_areaatendimento");

            entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

            entity.Property(e => e.DtAtualizacao)
                .IsRequired()
                .HasColumnName("dt_atualizacao")
                .HasColumnType("character varying");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.HasOne(d => d.CdAreaatendimentoNavigation)
                .WithMany(p => p.UsuarioAreaatendimento)
                .HasForeignKey(d => d.CdAreaatendimento)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("area_atendimento_usuario_areaatendimento_fk");

            entity.HasOne(d => d.CdUsuarioNavigation)
                .WithMany(p => p.UsuarioAreaatendimento)
                .HasForeignKey(d => d.CdUsuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("usuario_usuario_areaatendimento_fk");
        }
    }
}
