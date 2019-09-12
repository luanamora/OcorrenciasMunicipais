using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class ImagemUsuarioMap : IEntityTypeConfiguration<ImagemUsuario>
    {
        public void Configure(EntityTypeBuilder<ImagemUsuario> entity)
        {
            entity.HasKey(e => e.CdImagem);

            entity.ToTable("imagem_usuario");

            entity.Property(e => e.CdImagem).HasColumnName("cd_imagem");

            entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

            entity.Property(e => e.DsCaminho)
                .IsRequired()
                .HasColumnName("ds_caminho")
                .HasColumnType("character varying(500)");

            entity.Property(e => e.DsTipo)
                .IsRequired()
                .HasColumnName("ds_tipo")
                .HasColumnType("character varying(120)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.Property(e => e.NmImagem)
                .IsRequired()
                .HasColumnName("nm_imagem")
                .HasColumnType("character varying(255)");

            entity.HasOne(d => d.CdUsuarioNavigation)
                .WithMany(p => p.ImagemUsuario)
                .HasForeignKey(d => d.CdUsuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("usuario_imagem_usuario_fk");
        }
    }
}
