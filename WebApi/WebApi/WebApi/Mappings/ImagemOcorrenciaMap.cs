using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class ImagemOcorrenciaMap : IEntityTypeConfiguration<ImagemOcorrencia>
    {
        public void Configure(EntityTypeBuilder<ImagemOcorrencia> entity)
        {
            entity.HasKey(e => e.CdImagem);

            entity.ToTable("imagem_ocorrencia");

            entity.Property(e => e.CdImagem).HasColumnName("cd_imagem");

            entity.Property(e => e.CdOcorrencia).HasColumnName("cd_ocorrencia");

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

            entity.HasOne(d => d.CdOcorrenciaNavigation)
                .WithMany(p => p.ImagemOcorrencia)
                .HasForeignKey(d => d.CdOcorrencia)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("ocorrencia_imagem_ocorrencia_fk");
        }
    }
}
